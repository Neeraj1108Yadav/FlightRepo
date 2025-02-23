package androidx.compose.flight

import androidx.compose.flight.screens.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup(){
        viewModel = HomeViewModel()
    }

    @Test
    fun `update count should change to 2`(){
        viewModel.addPeople()
        assertEquals(2,viewModel.people)
    }

    @Test
    fun `update count should change to 3`(){
        viewModel.addPeople()
        assertEquals(3,viewModel.people)
    }

    @Test
    fun `update count should change to 4`(){
        viewModel.addPeople()
        assertEquals(4,viewModel.people)
    }
    @Test
    fun `update count should change to 5`(){
        viewModel.addPeople()
        assertEquals(5,viewModel.people)
    }

}