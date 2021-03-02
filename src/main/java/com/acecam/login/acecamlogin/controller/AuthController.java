package com.acecam.login.acecamlogin.controller;
import com.acecam.login.acecamlogin.entity.UserData;
import com.acecam.login.acecamlogin.entity.UserDataRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping(path="/api-ace-login")
public class AuthController {

    @Autowired
    UserDataRepository repository;

    @CrossOrigin
    @GetMapping (path="/auth")
    public @ResponseBody
    String loginAuth(@RequestParam String username, @RequestParam String password){
        String _STATUS,_ROLE = "";
        UserData userData = new UserData();
        userData = repository.findByUserName(username);
        if(userData != null){
            if(userData.getPassword().equals(password)){
                _STATUS = "Access Credentials";
                _ROLE = userData.getRole();
            }else{
                _STATUS = "(Password) Error Credentials";
            }
        }else{
            _STATUS = "(UserName) Error Credentials";
        }
        //return generateJsonSuccessMessageWithRandomToken(_STATUS,_ROLE);
        return _ROLE;
    }


    private JSONObject generateJsonSuccessMessageWithRandomToken(String x, String role){
        JSONObject jsonObject = new JSONObject();
        LocalDate date = LocalDate.now();
        jsonObject.put("status", x);
        jsonObject.put("token", givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect());
        jsonObject.put("time", date);
        if(!role.equals("")){
            jsonObject.put("role", role);
        }
        return jsonObject;
    }

    private String givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}

