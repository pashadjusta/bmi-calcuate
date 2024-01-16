
import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import com.example.tugasindividu.R


class MainActivity : Activity() {
    private var gender = "Laki - laki"
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }

    val calculateBtn = findViewById<Button>(R.id.calculateBtn)
    val resetBtn = findViewById<Button>(R.id.resetBtn)
    val editTextheight = findViewById<EditText>(R.id.editTextHeight)
    val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
    val genders = findViewById<RadioGroup>(R.id.radioGroupGender)
    val resultText = findViewById<TextView>(R.id.result)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateBtn.setOnClickListener {}
        resetBtn.setOnClickListener { resetInputs() }
    }

    private fun resetInputs() {
        editTextWeight.setText("")
        editTextheight.setText("")
    }

    private fun calculateBMI(
        editTextWeight: EditText,
        editTextHeight: EditText,
        radioGroupGender: RadioGroup,
        textViewResult: TextView
    ) {
        val height = editTextheight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()

        val selectedGender = genders.checkedRadioButtonId

        gender = when (selectedGender) {
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"
        }

        val bmi = when (gender) {
            "Laki-laki" -> weight / ((height / 100) * (height / 100))
            "Perempuan" -> weight / ((height / 100) * (height / 100)) * 0.9
            else -> 0.0
        }

        val result = "Hasil " + when {
            bmi < 18.5 -> "Kekurangan gizi"
            bmi >= 18.5 && bmi < 24.9 -> "Normal"
            bmi >= 25 && bmi < 29.9 -> "Kelebihan gizi"
            else -> "Obesitas"
        }

        resultText.text = "BMI:%.2f\n$result".format(bmi)
    }
}
