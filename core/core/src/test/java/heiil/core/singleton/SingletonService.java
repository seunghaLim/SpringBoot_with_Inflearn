package heiil.core.singleton;

public class SingletonService {

    //private 레벨로 자기 자신을 참조해서 넣어둠(클래스 멤버)
    private static final SingletonService instance  = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }


    //생성자를 private으로 해서 외부에서 이 객체를 새로 만들지 못하게 함 - 외부에서 얘를 못 만듦
    private SingletonService(){
        
    }
    
    // 결국 SingletinService의 객체를 만들 수 있는 것은 이 클래스 안에 instance 변수 하나뿐이게 됨

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");

    }
}
