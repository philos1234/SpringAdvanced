package hello.advanced.trace.strategy.code.template;

import hello.advanced.trace.strategy.code.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;


/**
 * 스프링내부에서 xxxTemplate로 구현된건 다 템플릿 콜백 패턴이다!
 */
@Slf4j
public class TimeLogTemplate {


    public void execute(Callback callback){

        long startTime = System.currentTimeMillis();
        callback.call(); //위임
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("rsultTime={}", resultTime);

    }

}
