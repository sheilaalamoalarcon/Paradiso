//EXAMPLE: val basicGrid = findViewById<LinearLayout>(R.id.game_view)

package com.alamo.paradiso

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.alamo.paradiso.R.string.in_game_points

interface ICell {
    val title: String
    fun onPress()
}

class MainActivity : ComponentActivity() {
    private var treasuresFound: Int = in_game_points


    private fun addButtons(selectedLayout: TableRow, numberOfButtons: Int){
        for (i in 0 until numberOfButtons) {
            val button = Button(selectedLayout.context)
            button.height = 200
            button.text = "Button $i"
            selectedLayout.addView(button)
        }
    }

    private fun addRows(selectedLayout: TableLayout, cells: Array<ICell>) {
        for (i in 0  until  4) {
            val tableRow = TableRow(selectedLayout.context)
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )

            addButtons(tableRow, 4)

            selectedLayout.addView(tableRow)
        }
    }

    private fun addPunctuationInfo(selectedLayout: TableLayout, treasuresFound:Number){
        val textView:TextView = TextView(selectedLayout.context)
        textView.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )


        textView.text = "$treasuresFound treasures found!"
        selectedLayout.addView(textView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        class Cell(override val title: String) : ICell {
            override fun onPress() {
                treasuresFound += 1
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table = findViewById<TableLayout>(R.id.game_view_table)

        val cells = Array<ICell>(10) { index ->
            Cell("Title $index")
        }

        fun updateTreasuresDisplay() {
            // Assuming you have a TextView with id `textViewTreasures`
            findViewById<TextView>(R.id.game_view_table)?.text = "$treasuresFound treasures found!"
        }

        // Llama a addRows con el TableLayout y el arreglo de celdas
        addRows(table, cells)

        addPunctuationInfo(table,treasuresFound)
    }
}

