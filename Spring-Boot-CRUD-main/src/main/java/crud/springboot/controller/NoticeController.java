package crud.springboot.controller;

import crud.springboot.model.Notice;
import crud.springboot.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notices")
    public String listNotices(Model model) {
        model.addAttribute("listNotices", noticeService.getAllNotices());
        return "index";
    }

    @GetMapping("/showNewNoticeForm")
    public String showNewNoticeForm(Model model) {
        Notice notice = new Notice();
        model.addAttribute("notice", notice);
        return "new_notice";
    }

    @PostMapping("/saveNotice")
    public String saveNotice(@ModelAttribute("notice") Notice notice) {
        noticeService.saveNotice(notice);
        return "redirect:/notices";
    }
}
