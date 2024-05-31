package com.openclassrooms.chatop.service.message;

import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.entity.Message;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.entity.User;
import com.openclassrooms.chatop.repository.MessageRepository;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseService;
import com.openclassrooms.chatop.service.jsonResponse.JsonResponseServiceImpl;
import com.openclassrooms.chatop.service.user.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	
	private final RentalRepository rentalRepository;

	private final UserRepository userRepository;

	private final UserService userService;

	private final MessageRepository messageRepository;

	private final ModelMapper modelMapper;

	@Override
	public JsonResponseService newMessage(CreateMessageDTO messageDTO) throws BadRequestException {

		Optional<Rental> rental = rentalRepository.findById(messageDTO.getRental_id());

		if (!rental.isPresent()) {
			throw new EntityNotFoundException("Rental not found");
		}

		Optional<User> user = userRepository.findById(messageDTO.getUser_id());

		if (!user.isPresent()) {
			throw new EntityNotFoundException("User not found");
		}

		if (!user.get().getId().equals(userService.getUser().getId())) {
			throw new BadRequestException("Invalid request");
		}

		Message message = modelMapper.map(messageDTO, Message.class);
		message.setRental(rental.get());
		message.setUser(user.get());
		
		messageRepository.save(message);

		return JsonResponseServiceImpl.builder().message("Message send with success").build();
	}

}
