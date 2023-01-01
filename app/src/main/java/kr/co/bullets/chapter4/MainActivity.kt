package kr.co.bullets.chapter4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import kr.co.bullets.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
//            intent.putExtra("intentMessage", "응급의료정보")
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
//            deleteData()
        }
    }

    private fun getDataAndUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "블렛츠")
            binding.birthdateValueTextView.text = getString(BIRTHDATE, "2022-10-30")
            binding.bloodTypeValueTextView.text = getString(BLOOD_TYPE, "Rh+ B")
            binding.emergencyContactValueTextView.text = getString(EMERGENCY_CONTACT, "010-1030-1030")

            val warning = getString(WARNING, "")
            binding.warningTextView.isVisible = warning.isNullOrEmpty().not()
            binding.warningValueTextView.isVisible = warning.isNullOrEmpty().not()
            if (!warning.isNullOrEmpty()) {
                binding.warningValueTextView.text = warning
            }
        }
    }

//    private fun deleteData() {
//        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
//            clear()
//            apply()
//            getDataAndUiUpdate()
//        }
//        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
//    }

    override fun onResume() {
        super.onResume()
        getDataAndUiUpdate()
    }
}