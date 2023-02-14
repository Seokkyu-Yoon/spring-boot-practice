package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RenderCountroller {
  @RequestMapping("/") 
  String main () {
    return "index.html";
  }
  @RequestMapping("/about") 
  String about () {
    return "about/index.html";
  }
}
