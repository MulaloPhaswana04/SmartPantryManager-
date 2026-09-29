# Smart Pantry Manager
Android Java app that suggests recipes strictly based on leftover pantry ingredients to cut food waste.

## Database: SQLite
Chose SQLite via SQLiteOpenHelper because it works offline, no internet needed, persists after app close/reopen, and matches module persistent data chapter. Simple CRUD for pantry items, lightweight for mobile.

## Setup
1. Open in Android Studio
2. Sync Gradle
3. Run on emulator (Pixel 5 API 33) or physical device
4. Add pantry items via + button
5. Go to Suggested Recipes to see strict-matched recipes

## Features
- Pantry CRUD (add/edit/delete)
- 20 pre-seeded Traditional well known South African recipes
- Strict matching: recipe only shown if ALL ingredients present
- Recipe Detail with ingredients & steps
- Settings with expiry alerts toggle