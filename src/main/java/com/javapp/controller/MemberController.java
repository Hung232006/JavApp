package com.javapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    // ================= DANH SÁCH =================
    @GetMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAll());
        return "listDV";
    }

    // ================= THÊM / SỬA (FORM) =================
    @PostMapping("/save")
    public String saveMember(@ModelAttribute Member member,
                             RedirectAttributes redirectAttributes) {

        memberService.save(member);
        redirectAttributes.addFlashAttribute("success", "Lưu thành công!");
        return "redirect:/member/list";
    }

    // ================= XÓA =================
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable String id,
                               RedirectAttributes redirectAttributes) {

        memberService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Đã xóa đoàn viên: " + id);
        return "redirect:/member/list";
    }

    // ================= FORM EDIT =================
    @GetMapping("/edit/{id}")
    public String editMember(@PathVariable String id, Model model) {

        Member member = memberService.getById(id);
        if (member == null) {
            return "redirect:/member/list";
        }

        model.addAttribute("member", member);
        return "index"; // form thêm/sửa
    }

    // ================= UPDATE (AJAX) =================
    @PutMapping("/{id}")
    @ResponseBody
    public Member updateMember(@PathVariable String id,
                               @RequestBody Member updated) {

        Member m = memberService.getById(id);
        if (m == null) {
            throw new RuntimeException("Không tìm thấy đoàn viên: " + id);
        }

        m.setFullName(updated.getFullName());
        m.setBirthDate(updated.getBirthDate());
        m.setGender(updated.getGender());
        m.setPhoneNumber(updated.getPhoneNumber());
        m.setEmailAddress(updated.getEmailAddress());
        m.setBranch(updated.getBranch());
        m.setPosition(updated.getPosition());
        m.setStatus(updated.getStatus());

        return memberService.save(m);
    }

    // ================= CHI TIẾT =================
    @GetMapping("/{id}")
    public String memberDetail(@PathVariable String id, Model model) {

        Member member = memberService.getById(id);
        if (member == null) {
            return "redirect:/member/list";
        }

        model.addAttribute("member", member);
        return "infor";
    }
}
