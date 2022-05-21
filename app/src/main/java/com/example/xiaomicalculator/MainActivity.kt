package com.example.calculatorxiaomi

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculatorxiaomi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()
    }


    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {

            appendText("0")
        }

        binding.btn1.setOnClickListener {

            appendText("1")
        }

        binding.btn2.setOnClickListener {

            appendText("2")
        }

        binding.btn3.setOnClickListener {

            appendText("3")
        }

        binding.btn4.setOnClickListener {

            appendText("4")
        }

        binding.btn5.setOnClickListener {

            appendText("5")
        }

        binding.btn6.setOnClickListener {

            appendText("6")
        }

        binding.btn7.setOnClickListener {

            appendText("7")
        }

        binding.btn8.setOnClickListener {

            appendText("8")
        }

        binding.btn9.setOnClickListener {

            appendText("9")
        }

    }

    private fun onOperatorClicked() {

        binding.btnAC.setOnClickListener {

            binding.txtJavab.text = ""
            binding.txtExpression.text = ""
        }

        binding.btnBSlash.setOnClickListener {

            if(binding.txtExpression.text.isNotEmpty())
            {
                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '*' &&
                    myChar != '/' &&
                    myChar != '+' &&
                    myChar != '-' ){

                    appendText("/")
                }
            }


        }

        binding.btnCP.setOnClickListener {

            appendText(")")
        }
        binding.btnOP.setOnClickListener {

            appendText("(")
        }

        binding.btnDot.setOnClickListener {

            if (binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) {
                appendText("0.")
            }else if (!binding.txtExpression.text.contains(".")){

                appendText(".")
            }
        }
        binding.btnMinos.setOnClickListener {

            if(binding.txtExpression.text.isNotEmpty())
            {
                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '*' &&
                    myChar != '/' &&
                    myChar != '+' &&
                    myChar != '-' ){

                    appendText("-")
                }
            }

        }

        binding.btnPlus.setOnClickListener {

            if(binding.txtExpression.text.isNotEmpty())
            {
                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '*' &&
                    myChar != '/' &&
                    myChar != '+' &&
                    myChar != '-' ){

                    appendText("+")
                }
            }
        }

        binding.btnStar.setOnClickListener {

            if(binding.txtExpression.text.isNotEmpty())
            {
                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '*' &&
                    myChar != '/' &&
                    myChar != '+' &&
                    myChar != '-' ){

                    appendText("*")
                }
            }
        }

        binding.btnBack.setOnClickListener {

           val oldText = binding.txtExpression.text.toString()

            if(oldText.isNotEmpty()){

                binding.txtExpression.text =oldText.substring(0,oldText.length - 1)

            }
        }

        binding.btnMosavi.setOnClickListener {

            try {
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()

                val result = expression.evaluate()

                val longResult = result.toLong()

                //135 = 135.0
                if(result == longResult.toDouble()){

                    binding.txtJavab.text = longResult.toString()
                } else{
                    binding.txtJavab.text = result.toString()
                }


            }catch (e : Exception){

                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this , " Error" , Toast.LENGTH_SHORT).show()
            }
            }






    }

    private fun appendText(newText: String) {

        if (binding.txtJavab.text.isNotEmpty()){

            binding.txtExpression.text = ""
        }

        binding.txtJavab.text = ""
        binding.txtExpression.append(newText)



        val viewTree : ViewTreeObserver = binding.horizantalScrollExpression.viewTreeObserver
        val viewTreeJavab : ViewTreeObserver = binding.horizantalScrollJavab.viewTreeObserver
        viewTree.addOnGlobalLayoutListener ( object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                binding.horizantalScrollExpression.viewTreeObserver.removeOnGlobalLayoutListener (this)
                binding.horizantalScrollExpression.scrollTo(binding.txtExpression.width , 0)
            }


        })
        viewTreeJavab.addOnGlobalLayoutListener ( object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                binding.horizantalScrollJavab.viewTreeObserver.removeOnGlobalLayoutListener (this)
                binding.horizantalScrollJavab.scrollTo(binding.txtJavab.width , 0)
            }


        })
        }

}


