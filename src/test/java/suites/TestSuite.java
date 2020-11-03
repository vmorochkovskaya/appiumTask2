package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTitleTest;
import tests.DeletionFromListTest;
import tests.SearchTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ArticleTitleTest.class,
        DeletionFromListTest.class,
        SearchTest.class
})
public class TestSuite {
}
