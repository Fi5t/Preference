package ru.freedomlogic.preferencesample

import android.support.v7.app.ActionBarActivity
import android.view.Menu
import android.view.MenuItem
import kotlin.properties.Delegates
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import ru.freedomlogic.preference.*
import android.widget.Button
import android.widget.Toast


public class MainActivity : ActionBarActivity() {
    val stringEdit  by Delegates.lazy { findViewById(R.id.edit_string)  as EditText }
    val integerEdit by Delegates.lazy { findViewById(R.id.edit_integer) as EditText }

    val radioTrue  by Delegates.lazy { findViewById(R.id.radio_true)   as RadioButton }
    val radioFalse by Delegates.lazy { findViewById(R.id.radio_false)  as RadioButton }

    val saveButton  by Delegates.lazy { findViewById(R.id.save)   as Button }
    val loadButton  by Delegates.lazy { findViewById(R.id.load)   as Button }
    val eraseButton by Delegates.lazy { findViewById(R.id.erase)  as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences(this) {
            onChanged { (_, key) ->
                Toast.makeText(getApplicationContext(), "Key: ${key}", Toast.LENGTH_SHORT).show()
            }
        }

        saveButton.setOnClickListener {
            preferencesEditor(this,
                    "string"  to stringEdit.getText().toString(),
                    "integer" to integerEdit.getText().toString().toInt(),
                    "boolean" to if (radioTrue.isChecked()) true else false
            )
        }

        loadButton.setOnClickListener {
            preferences(this) {
                stringEdit.setText(getString("string"))
                integerEdit.setText(getInt("integer").toString())

                if (getBoolean("boolean"))
                    radioTrue.setChecked(true)
                else
                    radioFalse.setChecked(true)
            }
        }

        eraseButton.setOnClickListener {
            preferencesEditor(this).erase()
            stringEdit.setText("")
            integerEdit.setText("")
            radioTrue.setChecked(true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
