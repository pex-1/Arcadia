<h2 align="center">🎮 Arcadia </h2>
<p align="center"> <strong>A modern Android app for browsing video games</strong><br/> Built with clean architecture, Jetpack Compose, and modern Android tools. </p>


# Features

- Infinite scrolling game list (Paging 3)
- Genre-based filtering
- Detailed game screens
- Offline-first with local caching
- Reactive UI with Kotlin Flow
- Smooth loading & error handling

# Architecture Overview
feature (UI)
│
core
├── domain
├── data
├── database
└── network


# Core principles

- State hoisting and UDF
- Domain is framework-agnostic
- Implementation details live outside domain
- Paging does not leak into domain

# Architectural Decisions
- Clean, pure domain layer

The domain layer contains:
- Domain models (Game, Genre, GameDetails)
- Repository interfaces
- Error & result abstractions

This keeps domain logic easy to test, stable and reusable

Paging stays out of the domain (by design)

Paging 3 library return type problem:

Flow<PagingData<Game>>

-domain shouldn't contain any android-specific dependencies


Instead:
- GameRepository → business operations
- GamePagingRepository → paging streams used by UI

Offline-first data flow
- Room is the single source of truth
- Network data is cached locally
- Paging loads from DB and syncs via RemoteMediator

Hybrid approach - feature and layer-based architecture

Each feature (game, gamedetails, genre) contains:
- Screen
- ViewModel
- Actions / UiState (when needed)
- Feature-specific UI components

# Modules
core/domain
- Business models
- Repository interfaces
- Error & result handling

core/data
- Repository implementations
- Paging repositories
- Data orchestration

core/database
- Room database
- Entities & DAOs
- Local data sources
- Database mappers

core/network
- Retrofit API
- DTOs & responses
- Network mappers
- OkHttp interceptors

feature/*
- Jetpack Compose UI
- ViewModels
- Feature-specific components

# Tech Stack
Language & Core

🟣 Kotlin
🔄 Coroutines
🌊 Flow

UI

🎨 Jetpack Compose
📐 Material 3

Architecture

🧩 Hilt
🧠 ViewModel
🏗 Clean Architecture (pragmatic)

Data

💾 Room
📜 Paging 3
⚙️ DataStore

Network

🌐 Retrofit
🔐 OkHttp
🧩 Moshi
