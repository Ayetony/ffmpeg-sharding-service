package com.github.tonydeng.fmj.runner;

import com.github.tonydeng.fmj.BaseTest;
import com.github.tonydeng.fmj.model.HLS;
import com.github.tonydeng.fmj.model.VideoFile;
import com.github.tonydeng.fmj.model.VideoInfo;
import com.github.tonydeng.fmj.utils.FFmpegUtils;
import com.github.tonydeng.fmj.utils.FileUtils;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by tonydeng on 15/4/16.
 */
@Ignore
public class FFmpegCommandRunnerTest extends BaseTest {

    private static final List<File> inputs_win = Lists.newArrayList(
            new File("D:\\temp\\test2.mp4"),
            new File("D:\\temp\\test.flv")
    );

    private static final List<File> inputs_linux = Lists.newArrayList(
            new File("/home/chichen/test/test.flv"),
            new File("/home/chichen/test//test2.mp4")
    );

    private static final List<File> inputs_mac = Lists.newArrayList(
            new File("/Users/ayetony.miao/Downloads/ElasticStack从入门到实践/23-3 实战（下）.avi")
//            new File("/Users/ayetony.miao/Downloads/尚学堂RocketMQ视频教程/01_RocketMQ视频第01部分/02_部分.mp4")
//            new File("/users/tonydeng/temp/m3u8/IMG_1666.MOV")
//            new File("/Users/tonydeng/temp/m3u8/IMG_1666.MOV")

    );

    private static List<File> inputs;

    @Before
    public void init() {
        String env = System.getProperty("os.name");
        if (null != env) {
            String os = env.toLowerCase();
            if (os.contains("win")) {
                log.info("****************** is WIN OS");
                inputs = inputs_win;
            } else if (os.contains("linux")) {
                log.info("****************** is LINUX OS");
                inputs = inputs_linux;
            } else if (os.contains("mac")) {
                log.info("****************** is MAC OS");
                inputs = inputs_mac;
            }
        }
    }

    @Test
    public void getVideoInfoTest() {
        for (File input : inputs) {
            VideoInfo vi = FFmpegCommandRunner.getVideoInfo(input);
            log.info("{} video info: {}", input.getAbsoluteFile(), vi.toString());
        }
    }

    @Test
    public void screenshotTest() {
        for (File input : inputs) {
            File output = FFmpegCommandRunner.screenshot(input,
                    10);
            assert output != null;
            log.info("input : {}, output:{}", input.getAbsoluteFile(), output.getAbsoluteFile());
        }
    }

    @Test
    public void generationHlsTest() {
        for (File input : inputs) {
            HLS hls = FFmpegCommandRunner.generationHls(input, 3, "http://dl.duoquyuedu.com/m3u8/" + FileUtils.getFileName(input) + "/");
            if (hls != null) {
                log.info("m3u8 path:'{}'", hls.getM3u8().getAbsolutePath());
                for (File ts : hls.getTs()) {
                    log.info("ts path:'{}'", ts.getAbsolutePath());
                }
            }
        }
    }

    @Test
    public void coverToMp4Test() {
        for (File input : inputs) {
            VideoFile vf = FFmpegCommandRunner.coverToMp4(input);
            if (vf.isSuccess())
                log.info("解析情况:" + vf);
        }
    }

    /**
     * -threads 5 -preset ultrafast  多线程
     * <p>
     * ffmpeg -i /Users/ayetony.miao/Downloads/ElasticStack从入门到实践/23-3\ 实战（下）.avi  -vf transpose=1 -c:v ibx264 -c:v libx264 -c:a aac  -strict -2 -threads 16 -preset ultrafast {.}_paraller.mp4
     */
    @Test
    public void parallelCoverMp4Test() {
        for (File input : inputs) {
            VideoInfo vi = FFmpegCommandRunner.getVideoInfo(input);
            List<String> commands = Lists.newArrayList(
                    "/usr/local/bin/ffmpeg",
                    "-i", input.getAbsolutePath(),
                    "-vf", "transpose=1",
                    "-c:v", "ibx264",
                    "-c:v", "libx264", "-c:a", "aac",
                    "-strict", "-2", "-threads", "16",
                    "-preset", "ultrafast",
                    "{.}_paraller.mp4"
            );
            log.info(FFmpegUtils.ffmpegCmdLine(commands));
            FFmpegCommandRunner.runProcess(commands);
        }
    }
}
