package BIF.SWE1.unittests;

import BIF.SWE1.interfaces.Url;
import org.junit.*;
import uebungen.UEB1;

import static org.junit.Assert.assertNotNull;

public class OwnTests extends AbstractTestFixture<UEB1> {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	protected UEB1 createInstance() {
		return new UEB1();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void url_should_return_path_segments() {
		Url obj = createInstance().getUrl("stacked/path/test.jpg?x=7#foobar");
		assertNotNull("UEB1.getSegments returned null", obj);

		//assertEquals("test", "test");
		String[] expected = {"stacked", "path", "test.jpg"};
		org.junit.Assert.assertEquals(expected, obj.getSegments());
	}

	@Test
	public void url_should_return_empty_path_segments() {
		Url obj = createInstance().getUrl("");
		assertNotNull("UEB1.getSegments returned null", obj);

		String[] expected = {};
		org.junit.Assert.assertEquals(expected, obj.getSegments());
	}

	@Test
	public void url_should_return_file_name() {
		Url obj = createInstance().getUrl("stacked/path/test.jpg?x=7");
		assertNotNull("UEB1.getFileName returned null", obj);

		org.junit.Assert.assertEquals("test.jpg", obj.getFileName());
	}

	@Test
	public void url_should_return_empty_file_name() {
		Url obj = createInstance().getUrl("stacked/path/folder?x=7");
		assertNotNull("UEB1.getFileName returned null", obj);

		org.junit.Assert.assertEquals("", obj.getFileName());
	}

	@Test
	public void url_should_return_file_extension() {
		Url obj = createInstance().getUrl("stacked/path/test.foo.jpg?x=7");
		assertNotNull("UEB1.getExtension returned null", obj);

		org.junit.Assert.assertEquals(".jpg", obj.getExtension());
	}

	@Test
	public void url_should_return_empty_extension() {
		Url obj = createInstance().getUrl("stacked/path/folder?x=7");
		assertNotNull("UEB1.getExtension returned null", obj);

		org.junit.Assert.assertEquals("", obj.getExtension());
	}

	@Test
	public void url_should_return_fragment() {
		Url obj = createInstance().getUrl("stacked/path/test.foo.jpg?x=7#foobar");
		assertNotNull("UEB1.getFragment returned null", obj);

		org.junit.Assert.assertEquals("foobar", obj.getFragment());
	}

	@Test
	public void url_should_return_empty_fragment() {
		Url obj = createInstance().getUrl("stacked/path/folder?x=7");
		assertNotNull("UEB1.getFragment returned null", obj);

		org.junit.Assert.assertEquals("", obj.getFragment());
	}

}
