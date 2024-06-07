package com.openclassrooms.chatop.service.message;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.Exception.EntityNotFoundException;
import com.openclassrooms.chatop.dto.message.CreateMessageDTO;
import com.openclassrooms.chatop.entity.Message;
import com.openclassrooms.chatop.entity.Rental;
import com.openclassrooms.chatop.entity.User;
import com.openclassrooms.chatop.repository.MessageRepository;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	
	private final RentalRepository rentalRepository;

	private final UserRepository userRepository;

	private final MessageRepository messageRepository;

	private final ModelMapper modelMapper;

	@Override
	public Message newMessage(CreateMessageDTO messageDTO) {

		Optional<Rental> rental = rentalRepository.findById(messageDTO.getRental_id());

		if (!rental.isPresent()) {
			throw new EntityNotFoundException("Rental not found");
		}

		Optional<User> user = userRepository.findById(messageDTO.getUser_id());

		if (!user.isPresent()) {
			throw new EntityNotFoundException("User not found");
		}

		Message message = modelMapper.map(messageDTO, Message.class);
		message.setRental(rental.get());
		message.setUser(user.get());
		
		return messageRepository.save(message);
	}

}
