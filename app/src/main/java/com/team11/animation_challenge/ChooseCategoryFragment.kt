package com.team11.animation_challenge


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.team11.animation_challenge.databinding.FragmentChooseCategoryBinding


class ChooseCategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentChooseCategoryBinding>(
                inflater, R.layout.fragment_choose_category, container, false)
        binding.data = this
        return binding.root
    }
    fun movies() {
        val url:String = "https://opentdb.com/api.php?amount=10&category=11&type=multiple"
        val category:String = "Entertainment: Film"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))
    }

    fun history() {
        val url = "https://opentdb.com/api.php?amount=10&category=23&type=multiple"
        val category:String = "History"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))

    }

    fun animals() {
        val url = "https://opentdb.com/api.php?amount=10&category=27&type=multiple"
        val category:String = "Animals"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))

    }

    fun geography() {
        val url = "https://opentdb.com/api.php?amount=10&category=22&type=multiple"
        val category:String = "Geography"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))

    }
    fun scienceMathematics() {
        val url = "https://opentdb.com/api.php?amount=10&category=19&type=multiple"
        val category:String = "Science: Mathematics"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))

    }
    fun generalKnowledge() {
        val url = "https://opentdb.com/api.php?amount=10&category=9&type=multiple"
        val category:String = "General Knowledge"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))

    }
    fun arts() {
        val url = "https://opentdb.com/api.php?amount=10&category=25&type=multiple"
        val category:String = "Art"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))
    }
    fun vehicles() {
        val url = "https://opentdb.com/api.php?amount=10&category=28&type=multiple"
        val category:String = "Vehicles"
        view?.findNavController()?.navigate(ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToTriviaQuestionFragment(url,category))

    }

}
