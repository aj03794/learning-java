package testing;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


// This is needed to make @Mock work
@ExtendWith(MockitoExtension.class)
class MyClassUsingSingletonTest {

    @Mock
    MyNaiveSingleton myNaiveSingleton;

    @Spy
    MyNaiveSingleton myNaiveSingleton2;

    @DisplayName("Testing static method")
    @Test
    public void t1() {

        // Given (setup)
        var expected = 6;
        // Line 34, 35, 36 all do the same thing - just a style preference
//        when(myNaiveSingleton.doSomething()).thenReturn(3);
        given(myNaiveSingleton.doSomething()).willReturn(3);
//        doReturn(2).when(myNaiveSingleton.doSomething());

        // When (exercise system)
        MyNaiveSingleton.setTestingInstance(myNaiveSingleton);
        MyClassUsingSingleton myClassUsingSingleton = new MyClassUsingSingleton();
        var res = myClassUsingSingleton.doSomething();

        // Then (verify)
//        verify(myNaiveSingleton).doSomething();
        then(myNaiveSingleton).should(times(1)).doSomething();
        assertEquals(expected, res);
    }

    @DisplayName("Using spy instead")
    @Test
    public void t2() {

        // Given (setup)
        var expected = 6;
        // Note that using given/when on a spy works differently than a doReturn (refer to README for more info)
        doReturn(3).when(myNaiveSingleton2).doSomething();
//        when(myNaiveSingleton2.doSomething()).thenReturn(3);
//        given(myNaiveSingleton2.doSomething()).willReturn(3);

        // When (exercise system)
        MyNaiveSingleton.setTestingInstance(myNaiveSingleton2);
        MyClassUsingSingleton myClassUsingSingleton = new MyClassUsingSingleton();
        var res = myClassUsingSingleton.doSomething();

        // Then (verify)
        then(myNaiveSingleton2).should(times(1)).doSomething();
        assertEquals(expected, res);
    }

}