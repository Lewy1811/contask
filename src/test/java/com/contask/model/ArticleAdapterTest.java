package com.contask.model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

//@Ignore
@RunWith(SpringRunner.class)
@JsonTest
public class ArticleAdapterTest {

    @Autowired
    private JacksonTester<ArticleFromNewsApi> jacksonTester;
    private ArticleAdapter articleAdapter;

    @Before
    public void init() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("ArticleFromNewsApiSample.json").getFile());
        ArticleFromNewsApi articleFromNewsApi = jacksonTester.readObject(file);

        articleAdapter = new ArticleAdapter(articleFromNewsApi);
    }

    @Test
    public void shouldGetDate() {
        assertTrue(articleAdapter.getDate().equals("2018-10-14"));
    }

    @Test
    public void shouldGetAuthor() throws Exception {
        assertTrue(articleAdapter.getAuthor().equals("Kamil J. Dudek"));
    }

    @Test
    public void shouldGetTitle() throws Exception {
        assertTrue(articleAdapter.getTitle().equals("A może Microsoft aktualizuje Windows 10 zbyt często? Opinie są podzielone"));
    }

    @Test
    public void shouldGetDescription() throws Exception {
        assertTrue(articleAdapter.getDescription().equals("Wydanie najnowszej wersji Windows 10, oznaczonej numerem 1809 wywołało w branży IT chwilowy skandal. Wersja okazała się mocno nieprzetestowana, zawierała…"));
    }

    @Test
    public void shouldGetSourceName() throws Exception {
        assertTrue(articleAdapter.getSourceName().equals("Dobreprogramy.pl"));
    }

    @Test
    public void shouldGetArticleUrl() throws Exception {
        assertTrue(articleAdapter.getArticleUrl().equals("https://www.dobreprogramy.pl/A-moze-Microsoft-aktualizuje-Windows-10-zbyt-czesto-Opinie-sa-podzielone,News,91450.html"));
    }

    @Test
    public void shouldGetImageUrl() throws Exception {
        assertTrue(articleAdapter.getImageUrl().equals("https://gallery.dpcdn.pl/imgd/News/85326/g_-_960x640_-_s_xff0ccc57-050b-49d3-b69e-4543c1d66feb.png"));
    }
}
