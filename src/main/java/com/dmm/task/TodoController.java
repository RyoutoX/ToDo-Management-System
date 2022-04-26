
package com.dmm.task;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')") // 追記 ROLE_USERのユーザのみアクセスを許可
    public String accessDeniedPage() {
        return "create";
    }
}