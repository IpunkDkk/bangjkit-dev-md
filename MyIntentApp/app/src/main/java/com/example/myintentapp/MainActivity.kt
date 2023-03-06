package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val selecteValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text =  "Hasil: $selecteValue"
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_with_data)
        btnMoveWithDataActivity.setOnClickListener(this)
        val btnMoveWithObject: Button = findViewById(R.id.btn_move_with_object)
        btnMoveWithObject.setOnClickListener(this)
        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)
        val btnMoveForResult:Button = findViewById(R.id.btn_move_with_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveaActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_with_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademi")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 4)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_move_with_object -> {
                val person = Person(
                    "DicodingAcademy",
                    5,
                    "academi@dicoding.com",
                    "Bandung"
                )
                Log.d("MainActivity", person.toString())
                val moveWithObject = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObject.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObject)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "085330043948"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_with_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}

