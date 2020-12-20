package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

//@Controller annotation is used to tell SpringBoot that the defined class is a controller which is used to route the client requests
//to specific methods
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //@RequestMapping is used to map the values entered in UI to variables in the controller
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title, @RequestParam("comment") String comment, HttpSession session, RedirectAttributes redirAttrs) {
        User user = (User) session.getAttribute("loggeduser");
        //creating an instance of a comment and assigning values to its variables
        Comment newComment = new Comment();
        newComment.setText(comment);
        newComment.setCreateDate(LocalDate.now());
        newComment.setUser(user);
        newComment.setImage(imageService.getImage(imageId));

        //Sending the comment object to service to persist in the database
        commentService.createComment(newComment);

        //Adding the attributes to redirect the request to /images/{imageId}/{title} controller
        redirAttrs.addAttribute("imageId", imageId).addFlashAttribute("imageId", imageId);
        redirAttrs.addAttribute("title", title).addFlashAttribute("title", title);

        return "redirect:/images/{imageId}/{title}";
    }
}