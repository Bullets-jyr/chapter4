package kr.co.bullets.chapter4

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kr.co.bullets.chapter4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intentMessage = intent.getStringExtra("intentMessage") ?: "없음"
//        Log.d("intentMessage", intentMessage)

        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthdateLayer.setOnClickListener {
//            val listener = OnDateSetListener { date, year, month, dayOfMonth ->
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthdateValueTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }

            DatePickerDialog(
                this,
                listener,
                1989,
                11,
                7
            ).show()
        }

        binding.warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.warningValueEditText.isVisible = isChecked
        }

        binding.warningValueEditText.isVisible = binding.warningCheckBox.isChecked

        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
//        val editor = getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit()
//        editor.putString("name", binding.nameValueEditText.text.toString())
//        editor.putString("bloodType", )
//        editor.putString("emergencyContact", binding.emergencyContactValueEditText.text.toString())
//        editor.putString("birthDate", binding.birthdateValueTextView.text.toString())
//        editor.putString("warning", )
//        editor.commit()
//        editor.apply()

        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameValueEditText.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(EMERGENCY_CONTACT, binding.emergencyContactValueEditText.text.toString())
            putString(BIRTHDATE, binding.birthdateValueTextView.text.toString())
            putString(WARNING, getWarning())
            apply()
        }

        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String {
        val bloodSign = if (binding.bloodTypePlus.isChecked) "Rh+" else "Rh-"
        val bloodAlphabet = binding.bloodTypeSpinner.selectedItem.toString()
        return "$bloodSign $bloodAlphabet"
    }

    private fun getWarning(): String {
        return if (binding.warningCheckBox.isChecked) binding.warningValueEditText.text.toString() else ""
    }
}