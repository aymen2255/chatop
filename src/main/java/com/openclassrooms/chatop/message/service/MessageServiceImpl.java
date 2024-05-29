package com.openclassrooms.chatop.message.service;

import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.jsonResponse.JsonResponse;
import com.openclassrooms.chatop.jsonResponse.JsonResponseImpl;
import com.openclassrooms.chatop.message.dto.CreateMessageDTO;
import com.openclassrooms.chatop.message.entity.Message;
import com.openclassrooms.chatop.message.repository.MessageRepository;
import com.openclassrooms.chatop.rental.entity.Rental;
import com.openclassrooms.chatop.rental.repository.RentalRepository;
import com.openclassrooms.chatop.user.entity.User;
import com.openclassrooms.chatop.user.repository.UserRepository;
import com.openclassrooms.chatop.user.service.UserService;
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
	public JsonResponse newMessage(CreateMessageDTO messageDTO) throws BadRequestException {

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

		return JsonResponseImpl.builder().message("Message send with success").build();
	}

}
