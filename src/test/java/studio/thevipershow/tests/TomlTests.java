package studio.thevipershow.tests;

import java.util.Objects;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;
import studio.thevipershow.fallensnow.config.snow.SnowValues;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class TomlTests {

    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static File tomlFile;
    private static TomlParseResult tomlParseResult;

    @Test
    public final void obtainResource() throws IOException {
        var url = classLoader.getResource("snow-settings.toml");
        assertNotNull("The resource file was null!", url);
        tomlFile = new File(url.getPath());

        assertTrue("The file did not exist or was not readable!", tomlFile.canRead() && tomlFile.isFile() && tomlFile.exists());
        System.out.println("Passed file reading phase!");
    }

    @Test
    public final void obtainTomlParseResult() throws IOException {
        tomlParseResult = Toml.parse(tomlFile.toPath());
        assertNotNull("Toml parse result is null", tomlParseResult);
        System.out.println("Passed file TOML parsing phase!");
    }

    @Test
    public final void checkAllReturnTypes() {
        for (final SnowValues value : SnowValues.values()) {
            var expectedClass = value.getReturnType();
            var objectObtained = tomlParseResult.get(value.getKey());
            assertNotNull(String.format("The object obtained from the config at key %s was null!", value.getKey()), objectObtained);
            var obtainedClass = objectObtained.getClass();
            assertTrue(String.format("The value was not correct! Got %s but expected %s", obtainedClass.getName(), expectedClass.getName()), expectedClass.isAssignableFrom(obtainedClass));
            System.out.println(objectObtained.toString());
        }

        System.out.println("All tests have been passed, congratulations!");
    }

}
