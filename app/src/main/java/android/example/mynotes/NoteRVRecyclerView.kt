package android.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteRVRecyclerView(private val context: Context, private val listner: INoteRVAdapter): RecyclerView.Adapter<NoteRVRecyclerView.NoteViewholder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textview: TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
        val viewHolder = NoteViewholder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listner.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) {
        val currentNote = allNotes[position]
        holder.textview.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun update(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INoteRVAdapter{
    fun onItemClicked(note:Note)
}