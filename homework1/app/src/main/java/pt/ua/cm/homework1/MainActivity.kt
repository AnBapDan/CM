package pt.ua.cm.homework1

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import kotlinx.coroutines.awaitAll


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var n1 = "969696969"
    var n2 = "969696969"
    var n3 = "969696969"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contact1: AppCompatButton = findViewById(R.id.contact1)
        val contact2:AppCompatButton = findViewById(R.id.contact2)
        val contact3:AppCompatButton = findViewById(R.id.contact3)
        val b0:AppCompatButton = findViewById(R.id.b0)
        val b1:AppCompatButton = findViewById(R.id.b1)
        val b2:AppCompatButton = findViewById(R.id.b2)
        val b3:AppCompatButton = findViewById(R.id.b3)
        val b4:AppCompatButton = findViewById(R.id.b4)
        val b5:AppCompatButton = findViewById(R.id.b5)
        val b6:AppCompatButton = findViewById(R.id.b6)
        val b7:AppCompatButton = findViewById(R.id.b7)
        val b8:AppCompatButton = findViewById(R.id.b8)
        val b9:AppCompatButton = findViewById(R.id.b9)
        val bSharp:AppCompatButton = findViewById(R.id.b_sharp)
        val bPlus:AppCompatButton = findViewById(R.id.b_plus)
        val clear: ImageButton = findViewById(R.id.clear)
        val call:ImageButton = findViewById(R.id.call)
        b0.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)
        bSharp.setOnClickListener(this)
        bPlus.setOnClickListener(this)
        clear.setOnClickListener(this)
        call.setOnClickListener(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS), 0)
        }


        contact1.setOnLongClickListener {
            pickContact1.launch(null)
            true
        }
        contact1.setOnClickListener {
            var intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:"+ Uri.encode(n1))
            startActivity(intent)

            true
        }
        contact2.setOnLongClickListener {
            pickContact2.launch(null)
            true
        }
        contact2.setOnClickListener {
            var intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:"+ Uri.encode(n2))
            startActivity(intent)
            true
        }
        contact3.setOnLongClickListener {
            pickContact3.launch(null)
            true
        }
        contact3.setOnClickListener {
            var intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:"+ Uri.encode(n3))
            startActivity(intent)
            true
        }

    }

    override fun onClick(button: View) {
        var screenNumber = findViewById<TextView>(R.id.dialer_number)
        if( button is AppCompatButton){
            screenNumber.text = screenNumber.text.toString() + button.text.toString()
        } else if (button is ImageButton){
            if(button.id == R.id.clear){
                screenNumber.text = screenNumber.text.toString().dropLast(1)
            }else {
                var intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:"+ Uri.encode(screenNumber.text.toString()))
                startActivity(intent)
            }
        }

    }

    @SuppressLint("Range")
    private val pickContact1 = registerForActivityResult(ActivityResultContracts.PickContact()){
            result ->
        run {

            val c = contentResolver.query(result!!, null, null, null, null)
            if (c!!.moveToFirst()) {

                n1 = c.getString(10).replace(" ", "").split(",")[0]
                findViewById<AppCompatButton>(R.id.contact1).text = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            }
        }

    }
    @SuppressLint("Range")
    private val pickContact2 = registerForActivityResult(ActivityResultContracts.PickContact()){
            result ->
        run {

            val c = contentResolver.query(result!!, null, null, null, null)
            if (c!!.moveToFirst()) {

                n2 = c.getString(10).replace(" ", "").split(",")[0]
                findViewById<AppCompatButton>(R.id.contact2).text = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            }
        }

    }
    @SuppressLint("Range")
    private val pickContact3 = registerForActivityResult(ActivityResultContracts.PickContact()){
            result ->
        run {

            val c = contentResolver.query(result!!, null, null, null, null)
            if (c!!.moveToFirst()) {

                n3 = c.getString(10).replace(" ", "").split(",")[0]
                findViewById<AppCompatButton>(R.id.contact3).text = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            }
        }

    }

}