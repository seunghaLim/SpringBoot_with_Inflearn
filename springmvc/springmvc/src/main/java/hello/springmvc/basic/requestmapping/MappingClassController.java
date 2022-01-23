package hello.springmvc.basic.requestmapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mapping/users")
public class MappingClassController {

    /*
    회원 관리 API
    회원 목록 조회: GET /users
    회원 등록: POST /users
    회원 조회: GET /users/{userId}
    회원 수정: PATCH /users/{userId}
    회원 삭제: DELETE /users/{userId}
     */

    // 회원 목록 조회
    @GetMapping
    public String user(){
        return "get users";
    }

    // 회원등록
    @PostMapping
    public String AddUsers(){
        return "post users";
    }

    // 아이디로 회원 찾기
    @GetMapping("/{usersId}")
    public String findUser(@PathVariable String usersId){
        return "get userId= " + usersId;
    }

    // 회원 수정
    @PatchMapping("/{usersId}")
    public String updateUser(@PathVariable String usersId){
        return "update userId= " + usersId;
    }

    // 회원 삭제
    @DeleteMapping("/{usersId}")
    public String deleteUser(@PathVariable String usersId){
        return "delete usersId= " + usersId;
    }


}
