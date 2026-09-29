package com.kiryomanz.app

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class KiryoWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val prefs = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
        val lines = prefs.getString("flutter.widget_favorites_lines", null)
            ?: "Buka KIRYO NIME untuk update widget"
        val cont = prefs.getString("flutter.widget_continue_title", null)
        val body = buildString {
            if (!cont.isNullOrBlank()) {
                append("Lanjut: ")
                append(cont)
                append("\n\n")
            }
            append("Favorit:\n")
            append(lines)
        }
        for (id in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.kiryo_widget)
            views.setTextViewText(R.id.widget_title, "KIRYO NIME")
            views.setTextViewText(R.id.widget_body, body)
            appWidgetManager.updateAppWidget(id, views)
        }
    }
}
