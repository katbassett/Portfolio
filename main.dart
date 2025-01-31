import 'package:flutter/material.dart';
import 'widgets/custom_app_bar.dart';
import 'widgets/home_productcards.dart';
import 'widgets/home_banner.dart';
import 'widgets/custom_footer.dart';
import 'dog_products.dart';

/// Author: Kat Bassett
///

void main() => runApp(const AppBarApp());

/// Author: Kat Bassett
///

class AppBarApp extends StatelessWidget {
  const AppBarApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.grey,
        ),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'dawgs'),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        title: title,
        onPressed: () {
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(content: Text('Search clicked')),
          );
        },
        menuAction: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const ProductsScreen(),
            ),
          );
        },
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            // Search Bar
            Container(
              color: Colors.grey[200],
              padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
              child: TextField(
                decoration: InputDecoration(
                  filled: true,
                  fillColor: Colors.white,
                  hintText: "Search for pet supplies...",
                  prefixIcon: const Icon(Icons.search),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(30),
                    borderSide: BorderSide.none,
                  ),
                ),
              ),
            ),

            // Home Banner
            const HomeBanner(),

            // Killer Deals Section
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 20),
              child: const KillerDealsSection(),
            ),

            const CustomFooter(),
          ],
        ),
      ),
    );
  }
}
