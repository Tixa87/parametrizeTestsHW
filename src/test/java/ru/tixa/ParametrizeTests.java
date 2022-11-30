package ru.tixa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizeTests {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("https://firstlinesoftware.com/");
    }

    // ТЕСТ 1 @CsvSource

    @Disabled //тест скрыт пока разбираюсь с csv файлом
    @DisplayName("Main menu sections")
    @CsvSource({
            "Industries", "Services", "Insights", "About Us"
    })

    @ParameterizedTest(name = "Check sections {0} in  main menu")

    void flsMainMenuTest(String section) {
        $(".nav").shouldHave(text(section));

    }

     // ТЕСТ 2 @MethodSource

    @Disabled //тест скрыт пока разбираюсь с csv файлом
    static Stream<Arguments> subSectionsInMainMenu() {
        return Stream.of(
                Arguments.of("Industries", List.of("HEALTHCARE", "LOGISTICS", "MANUFACTURING", "RETAIL DIGITALIZATION", "WAREHOUSE MANAGEMENT")));


    }
    @MethodSource
    @ParameterizedTest(name="subSection {1} is in MainMenu {0}" )


    void subSectionsInMainMenu (String section, List<String> subsections){
        $("#menu-item-195").click();
        $$(".aos-animate a").filter(visible).shouldHave(texts(subsections));

    }

     // ТЕСТ3 @CsvFileSource

    @CsvFileSource(resources = "filemainmenu.csv")
    @ParameterizedTest(name="section {0} opens page with header {1}")

    void subSectionsInMainMenuFile ( String section, String header) {
        $(".nav").$(byText(section)).click();
        $$(".headline h1").filter(visible).shouldHave(texts(header));
    }
}


