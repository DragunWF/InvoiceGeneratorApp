# Invoice Generator App

## 📌 Overview
The **Invoice Manager App** is a lightweight Android application designed to streamline invoice creation and management for small businesses and freelancers. This project was developed as a **practice project for a hackathon**, emphasizing quick development, core functionality, and usability.

## 🚀 Features
### Invoice Management
- 📄 **Create and save invoices** with essential details:
  - Client name and contact information
  - Auto-generated invoice number
  - Date issued and due date
  - Itemized list with descriptions, quantities, and prices
  - Subtotal, tax, and total amount calculations
- 📂 **View and manage invoices**:
  - Store and retrieve invoice history
  - Edit draft invoices
  - Delete invoices when necessary
- 📤 **Share invoices** via messaging apps or email

### Data Persistence
- 🔄 **SharedPreferences** used to store invoice data locally
- 📌 Saves recent client details for quick access
- 🔢 Maintains invoice number sequences
- 🏢 Stores business information (address, contact info)
- 📝 Caches draft invoices for later completion

## 🛠️ Tech Stack
- **Android Studio** (Java/Kotlin)
- **SharedPreferences** (JSON storage)
- **RecyclerView** for itemized invoice lists
- **Validation** for numeric inputs (prices, quantities)

## 📲 User Flow
1. **Dashboard**: Quick access to recent invoices and invoice creation
2. **Create Invoice**: Enter client details, items, and pricing
3. **Manage Invoices**: View, edit, and delete invoices
4. **Share Invoice**: Export and send invoices to clients

## ⚡ Development Constraints
- ⏳ Designed to be completed within **2 hours** by **two developers**
- 🎯 Focus on **core invoice management workflow**
- 🔍 Prioritizes **data integrity and usability**

## ✅ Success Criteria
This project is considered successful if it:
- ✔️ Allows users to **create, view, edit, and share** invoices
- ✔️ Performs **accurate tax and total calculations**
- ✔️ Maintains **data persistence** across sessions
- ✔️ Presents a **clean and business-friendly UI**
- ✔️ Can be developed within the **2-hour hackathon timeframe**

---
This project was developed as part of a **hackathon practice challenge** to refine rapid development skills and problem-solving in Android app development.
