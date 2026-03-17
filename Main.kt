
data class Contact(
    val name: String,
    val phone: String,
    val email: String?,   // nullable
    val isFavorite: Boolean
)


fun main() {

    val contacts = mutableListOf<Contact>()

    while (true) {
        println("\n--- Contact Manager ---")
        println("1. Add Contact")
        println("2. View All Contacts")
        println("3. View Favorite Contacts")
        println("4. View Contacts with Email")
        println("5. Exit")

        when (readLine()?.toIntOrNull()) {

            1 -> addContact(contacts)

            2 -> displayContacts(contacts)

            3 -> {
                val favorites = filterContacts(contacts) { it.isFavorite }
                println("\n⭐ Favorite Contacts:")
                displayContacts(favorites)
            }

            4 -> {
                val emailContacts = filterContacts(contacts) { it.email != null }
                println("\n📧 Contacts with Email:")
                displayContacts(emailContacts)
            }

            5 -> {
                println("Exiting...")
                break
            }

            else -> println("Invalid choice!")
        }
    }
}


fun addContact(contacts: MutableList<Contact>) {
    println("Enter Name:")
    val name = readLine() ?: ""

    println("Enter Phone:")
    val phone = readLine() ?: ""

    println("Enter Email (or press Enter to skip):")
    val emailInput = readLine()
    val email = if (emailInput.isNullOrBlank()) null else emailInput

    println("Is Favorite? (yes/no):")
    val isFavorite = readLine()?.lowercase() == "yes"

    contacts.add(Contact(name, phone, email, isFavorite))
    println("Contact added successfully!")
}


fun displayContacts(list: List<Contact>) {
    if (list.isEmpty()) {
        println("No contacts found.")
        return
    }

    for (contact in list) {
        println("----------------------")
        println("Name: ${contact.name}")
        println("Phone: ${contact.phone}")
        println("Email: ${contact.email ?: "Not Provided"}")
        println("Favorite: ${if (contact.isFavorite) "Yes" else "No"}")
    }
}


fun filterContacts(
    contacts: List<Contact>,
    condition: (Contact) -> Boolean
): List<Contact> {
    return contacts.filter(condition)
}