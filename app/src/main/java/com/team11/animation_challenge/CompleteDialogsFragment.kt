package com.team11.animation_challenge

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.team11.animation_challenge.databinding.FragmentTriviaQuestionBinding

class CompleteDialogsFragment: DialogFragment() {
    lateinit var binding:FragmentTriviaQuestionBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate<FragmentTriviaQuestionBinding>(inflater,R.layout.complete_questions_result,container,false)
        return binding.root
    }

//    private fun showCompletedDialog(mcontext: Context?) {
//    dialogBuilder = mcontext?.let { AlertDialog.Builder(it) }
//    val layoutView = layoutInflater.inflate(R.layout.complete_questions_result, null)
//    var dialogButton = layoutView.findViewById(R.id.button_dialog) as Button
//    val resultText = layoutView.findViewById(R.id.result_text) as TextView
//    val praiseText = layoutView.findViewById(R.id.praise_text) as TextView
//
//    dialogBuilder?.setView(layoutView)
//    completedDialog = dialogBuilder?.create()
//    completedDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    resultText.text = getString(R.string.result_Info, correct, 11)
//    when {
//        correct!! < 5 -> praiseText.setText(R.string.less_than_5)
//        correct!! in 5..7 -> praiseText.setText(R.string.five_to_7)
//        correct!! in 9..10 -> praiseText.setText(R.string.eight_to_10)
//        else -> praiseText.setText(R.string.perfect_score)
//    }
//    completedDialog!!.show()
//    dialogButton.setOnClickListener{
//        completedDialog!!.dismiss()
//        val i = Intent(mcontext, TriviaActivity::class.java)
//        startActivity(i)
//    }
//}
}