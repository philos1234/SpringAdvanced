package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 붙은걸 롬복이 자동으로 생성자 만들어준다
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;

    public void orderItem(String itemId){
        orderRepository.save(itemId);
    }


}
