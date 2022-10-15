package Service;

import Model.Message;
import Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return (List<Message>) messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> messageFound = messageRepository.getMessage(message.getIdMessage());
            if (messageFound.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> messageFound = messageRepository.getMessage(message.getIdMessage());
            if (!messageFound.isEmpty()) {
                if (message.getMessageText() != null) {
                    messageFound.get().setMessageText(message.getMessageText());
                }
                return messageRepository.save(messageFound.get());
            }
        }
        return message;
    }

    public boolean deleteMessage(int messageId){
        Boolean result = getMessage(messageId).map(messageToDelete ->{
            messageRepository.delete(messageToDelete);
            return true;
        }).orElse(false);
        return result;
    }
}
