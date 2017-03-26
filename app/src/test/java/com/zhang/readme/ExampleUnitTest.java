package com.zhang.readme;

import com.zhang.readme.provider.impl.ChapterProvider_52BiQuGe;
import com.zhang.readme.provider.impl.ChapterProvider_8DuShu;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.print(new ChapterProvider_52BiQuGe("http://www.52biquge.com/biquge/52/52982/2838727.html").getBookChapterText());
    }
}