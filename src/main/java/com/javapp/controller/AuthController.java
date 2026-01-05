package com.javapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javapp.dao.UserRepository;
import com.javapp.model.User;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UserRepository userRepository;
        @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model
    ) {
        if (userRepository.existsByUsername(username)) {
            model.addAttribute("error", "Username đã tồn tại");
            return "register";
        }

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email đã tồn tại");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // ⚠️ sau này sẽ mã hóa

        userRepository.save(user); // 💾 LƯU DATABASE

        return "redirect:/";
    }
     public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // POST login
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password
    ) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            // ✅ ĐÚNG TÀI KHOẢN → VỀ INDEX
            return "redirect:/";
        }

        // ❌ SAI → quay lại login
        return "redirect:/auth/login?error";
    }
    
}
