DiaryVaultMVP Calendar Patch
===========================
Adds Calendar UI + entry browsing.

Steps:
1) Copy patch files into your existing project root (DiaryVaultMVP_FULL)
2) Add dependency in app/build.gradle.kts:
   implementation("com.prolificinteractive:material-calendarview:2.0.1")
3) Register activities in AndroidManifest.xml:
   CalendarActivity, EntryListActivity, ViewEntryActivity
4) Build:
   ./gradlew clean assembleDebug
