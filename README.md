# Smart Pantry Manager
Android Java app that suggests recipes strictly based on leftover pantry ingredients to cut food waste.

## Database: SQLite
Chose SQLite via SQLiteOpenHelper over Firebase/PostgreSQL because it works fully offline (no internet needed in kitchen), persists after app close/reopen (file-based), lightweight (<1MB, fits 50MB ZIP limit), taught in module persistent data chapter, and needs no REST API. Simple CRUD for pantry items, ideal for mobile.

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

GitHub: [SmartPantryManager](https://github.com/MulaloPhaswana04/SmartPantryManager-)
Commits: 12 incremental commits showing build from model -> CRUD -> RecyclerView -> validation -> recipes -> strict-matching -> detail -> settings
