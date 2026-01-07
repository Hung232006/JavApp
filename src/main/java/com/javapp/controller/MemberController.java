package com.javapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javapp.Service.MemberService;
import com.javapp.model.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // List tất cả member
    @GetMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAll());
        return "listDV";
    }

    // Lưu member mới hoặc sửa
    @PostMapping("/save")
    public String saveMember(
            @ModelAttribute Member member,
            RedirectAttributes redirectAttributes
    ) {
        memberService.save(member);
        redirectAttributes.addFlashAttribute("success", "Lưu thành công!");
        return "redirect:/member/list";
    }

    // Xóa member
    @GetMapping("/delete/{id}")
    public String deleteMember(
            @PathVariable("id") String memberID,
            RedirectAttributes redirectAttributes
    ) {
        memberService.delete(memberID);
        redirectAttributes.addFlashAttribute("success", "Xóa thành công đoàn viên: " + memberID);
        return "redirect:/member/list";
    }

    // Sửa member (trả về index.html để chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String editMember(@PathVariable("id") String memberID, Model model) {
        Member member = memberService.getById(memberID);
        if (member == null) {
            return "redirect:/member/list";
        }
        model.addAttribute("member", member);
        return "index";
    }

    // Xem chi tiết member trên trang mới
    @GetMapping("/{id}")  // URL: /member/{id}
    public String memberDetail(@PathVariable String id, Model model) {
        Member member = memberService.getById(id);
        if (member == null) {
            return "redirect:/member/list"; // nếu không có member thì quay lại list
        }
        model.addAttribute("member", member);
        return "infor"; // trả về trang infor.html
    }
}
