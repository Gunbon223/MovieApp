package org.gb.movieapp.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dashboard")
public class DashboardController {
    @GetMapping("")
    public String getAdminPage() {
        return "/admin/dashboard";
    }
}
