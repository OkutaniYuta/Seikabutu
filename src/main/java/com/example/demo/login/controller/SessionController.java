package com.example.demo.login.controller;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SessionController {
    private final UserService userService;

    @GetMapping("/session")
    public void getSessionController(@AuthenticationPrincipal UserDetails auth, HttpServletResponse response,
                                     HttpServletRequest request) throws ServletException, IOException {
        String email = auth.getUsername();
        User user = userService.getEmailByEmail(email);
        int role = user.getRole();
        int userStatus = user.getUserStatus();
        int userId = user.getUserId();

        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        session.setAttribute("userId", userId); // セッションにuserIdを登録
        userId = (int) session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
        List<Contract> userList = userService.getOnlyContractByUserId(userId);// UserIdをキーにコントラクトテーブルのみを全件リスト型で取得

        String redirect;
        if (role == 0) {
            redirect = "/adminHome";
        } else if (userStatus == 1 && !userList.isEmpty()) {//　&&　コントラクトリストのみをListで取得して、List sizeが1以上なら、Home画面に遷移する。
            redirect = "/home";
        } else {
            redirect = "/contractInsert";
        }
        response.sendRedirect(redirect);
    }

}
