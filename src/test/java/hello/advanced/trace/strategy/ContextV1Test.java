package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("rsultTime={}", resultTime);
    }


    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("rsultTime={}", resultTime);
    }


    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1(){

        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1); //전략 1로 주입
        context1.execute();


        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();


    }



    @Test
    void strategyV2(){
        //익명 클래스로 전략 구현

        Strategy strategyLogic1 = new Strategy(){
            @Override
            public void call(){
                log.info("비즈니스 로직1 실행");
            }
        };
        ContextV1 context1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}",strategyLogic1.getClass());
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        ContextV1 context2= new ContextV1(strategyLogic2);
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        context2.execute();
    }

    /**
     *
     * 내부에 바로 생성, 조금 더 간결
     */
    @Test
    void strategyV3(){
        ContextV1 context1 = new ContextV1(new Strategy(){
            @Override
            public void call(){
                log.info("비즈니스 로직1 실행");
            }
        });

        context1.execute();

        ContextV1 context2= new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });

        context2.execute();
    }
    
    
    /**
     * 자바 람다식 이용
     * 인터페이스에 메서드가 하나만 있어야함
     */
    @Test
    void strategyV4(){
        ContextV1 context1 = new ContextV1(()-> log.info("비즈니스 로직1 실행"));
        context1.execute();
        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        context2.execute();

    }


}
