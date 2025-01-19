package com.bechir.springbootaws.Controllers;


import com.bechir.springbootaws.Services.Image_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

        @Autowired
        private Image_service imgSvc;

        @RequestMapping()
        public String home(Model model) {
            model.addAttribute("images", imgSvc.getAllImages());
            return "index.html";
        }
        @RequestMapping("/upload")
        public String upload() {
            return "UploadImage.html";
        }


}
