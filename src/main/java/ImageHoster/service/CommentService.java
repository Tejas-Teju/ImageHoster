package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service annotation is used to tell springBoot that the defined class is service and is available for used in the container
@Service
public class CommentService {
    //@Autowired annotation is used to use repository class in the service
    @Autowired
    private CommentRepository repository;

    //Call the createComment() method in the Repository and obtain Comment object from it where this object is available for the model
    //to send it to view
    public void createComment(Comment newComment) {
        repository.createComment(newComment);
    }
}
