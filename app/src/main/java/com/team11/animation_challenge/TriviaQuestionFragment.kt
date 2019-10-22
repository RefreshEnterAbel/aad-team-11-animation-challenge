package com.team11.animation_challenge

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.team11.animation_challenge.databinding.FragmentTriviaQuestionBinding
import android.text.Html
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_trivia_question.*
import okhttp3.*
import java.io.IOException
import java.util.Collections.shuffle

/**
 * A simple [Fragment] subclass.
 */
class TriviaQuestionFragment : Fragment(), View.OnClickListener {


    var constant = 0
    var count = 0
    var questionNumber = 0
    var question_num :String? = null
    var question :String? = null
    var correct:Int = 0
    var Mcontext:Context? = null
    lateinit var questionCategory :String
    var correctAnswer :String? = null
    var incorrectAnswer1 :String? = null
    var incorrectAnswer2 :String? = null
    var incorrectAnswer3  :String?= null
    lateinit var result :TriviaRequestResults
    lateinit var triviaRequest:TriviaRequest
    lateinit var binding:FragmentTriviaQuestionBinding
    var button_ans1:String? = null
    var button_ans2:String? = null
    var button_ans3:String? = null
    var button_ans4:String? = null
    var visible:String? = null
    private var completedDialog: AlertDialog? = null
    private var dialogBuilder: AlertDialog.Builder? = null
    private val client = OkHttpClient()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentTriviaQuestionBinding>(
                inflater, R.layout.fragment_trivia_question, container, false)
        var args = arguments?.let { TriviaQuestionFragmentArgs.fromBundle(it) }
        (activity as AppCompatActivity).supportActionBar?.title = "${args?.category}"
         Mcontext = context
        binding.progressBar.setVisibility(View.VISIBLE)
        try {
           fetch(args?.url.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setVisible()
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.invalidateAll()
        binding.dataBanding = this
        return binding.root
    }
     fun nextButton(){
         if (questionNumber <= 9){
             constant  = 0
             count = 0
             button1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
             button2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
             button3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
             button4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
             progressBarCounter.setProgress(questionNumber + 1);
             result = triviaRequest?.results?.get(questionNumber)!!
             setQuestion(result)
             binding.invalidateAll()
         }else{
             showCompletedDialog()
         }

    }
    private fun showCompletedDialog() {
    dialogBuilder = context?.let { AlertDialog.Builder(it) }
    val layoutView = layoutInflater.inflate(R.layout.complete_questions_result, null)
    var dialogButton = layoutView.findViewById(R.id.button_dialog) as Button
    val resultText = layoutView.findViewById(R.id.result_text) as TextView
    val praiseText = layoutView.findViewById(R.id.praise_text) as TextView

    dialogBuilder?.setView(layoutView)
    completedDialog = dialogBuilder?.create()
    completedDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    resultText.text = getString(R.string.result_Info, correct, 10)
    when {
        correct!! < 5 -> praiseText.setText(R.string.less_than_5)
        correct!! in 5..7 -> praiseText.setText(R.string.five_to_7)
        correct!! in 9..10 -> praiseText.setText(R.string.eight_to_10)
        else -> praiseText.setText(R.string.perfect_score)
    }
    completedDialog!!.show()
    dialogButton.setOnClickListener{
        completedDialog!!.dismiss()
        val i = Intent(context, TriviaActivity::class.java)
        startActivity(i)
    }

    }
    fun setVisible(){
        binding.progressBarCounter.setVisibility(View.VISIBLE)
        binding.questioncount.setVisibility(View.VISIBLE)
        binding.questiontext.setVisibility(View.VISIBLE)
        binding.button1.setVisibility(View.VISIBLE)
        binding.button2.setVisibility(View.VISIBLE)
        binding.button3.setVisibility(View.VISIBLE)
        binding.button4.setVisibility(View.VISIBLE)
        binding.button.setVisibility(View.VISIBLE)
    }
      fun fetch(url:String){
          Log.d("url" ,"$url")
         val request = Request.Builder()
                 .url(url)
                 .build()
         client.newCall(request).enqueue(object : Callback {
             override fun onFailure(call: Call, e: IOException) {
                 e.printStackTrace()
             }
             @Throws(IOException::class)
             override fun onResponse(call: Call, response: Response) {
                 response.body!!.use { responseBody ->
                     if (!response.isSuccessful)
                         throw IOException("Unexpected code $response")
                     val builder = GsonBuilder()
                     val gson = builder.create()
                     assert(responseBody != null)
                     val body = responseBody.string()
                     Log.d("body","$body")
                     triviaRequest = gson.fromJson(body, TriviaRequest::class.java)
                     result = triviaRequest?.results?.get(questionNumber)!!
                     binding.progressBar.setVisibility(View.INVISIBLE)
                     setVisible()
                     setQuestion(result)
                 }
             }
         })
    }

    fun setQuestion(triviaRequestResults: TriviaRequestResults?)
    {
        Log.d("questionNumber ","$questionNumber")
        question_num = "Question  ${questionNumber + 1}/10"
        question = Html.fromHtml(triviaRequestResults?.question).toString()
        Log.d("Question","$question")
        questionCategory = Html.fromHtml(triviaRequestResults?.category).toString()
        correctAnswer = Html.fromHtml(triviaRequestResults?.correct_answer).toString()
        incorrectAnswer1 =  Html.fromHtml(triviaRequestResults?.incorrect_answers?.get(0)).toString()
        incorrectAnswer2 =  Html.fromHtml(triviaRequestResults?.incorrect_answers?.get(1)).toString()
        incorrectAnswer3 =  Html.fromHtml(triviaRequestResults?.incorrect_answers?.get(2)).toString()
        val questions: List<String?> = listOf(correctAnswer,incorrectAnswer1,incorrectAnswer2,incorrectAnswer3)
        Log.d("questions", questions.toString())
        shuffle(questions)
        button_ans1= questions[0]
        button_ans2 = questions[1]
        button_ans3 = questions[2]
        button_ans4 = questions[3]
        binding.invalidateAll()
    }
    override fun onClick(v: View?) {
        var mButton:Button = v as Button
        if (mButton.text.toString() == correctAnswer) {
            if (constant == 0){
                //correct action put this hear
                questionNumber++
                constant++
                if (count == 0) {
                    correct++
                }
            }else questionNumber
            mButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_black_24dp, 0)
        } else {
            //incorrect Animation hear
            mButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_wrong_black_24dp, 0)
        }
        count++
    }
}
