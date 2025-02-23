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
    fun `update count upto 5 success`(){
        viewModel.addPeople()
        assertEquals(2,viewModel.people)

        viewModel.addPeople()
        assertEquals(3,viewModel.people)

        viewModel.addPeople()
        assertEquals(4,viewModel.people)

        viewModel.addPeople()
        assertEquals(5,viewModel.people)
    }

    @Test
    fun `people fly count less than max people`(){
        for(i in 1..3){
            viewModel.addPeople()
        }

        assertEquals(4,viewModel.people)
        assertEquals(false,viewModel.isValidPeopleCount)
    }

    @Test
    fun `people fly count greater than max people`(){
        for(i in 1..4){
            viewModel.addPeople()
        }

        assertEquals(5,viewModel.people)
        assertEquals(true,viewModel.isValidPeopleCount)
    }
}