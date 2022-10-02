package kr.or.springbatch.study.chapterone;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ChapterOneConfiguration {

    /* lombok이 RequiredArgsConstructor 어노테이션을 통해 final이 붙은 클래스나 변수를 생성자를 통해 주입받을 수 있도록 만들어 줌*/
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chapterOneJob(){
        return jobBuilderFactory.get("chapterOneJob")
                .start(chapterOneStep())
                .next(chapterTwoStep())
                .build();
    }

    @Bean
    public Step chapterOneStep() {
        return stepBuilderFactory.get("chapterOneStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println(" ===================== ");
                        System.out.println(" 여기 잘 탄다. ");
                        System.out.println(" ===================== ");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step chapterTwoStep() {
        return stepBuilderFactory.get("chapterTwoStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println(" ===================== ");
                        System.out.println(" 두번째꺼 잘 탄다. ");
                        System.out.println(" ===================== ");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
