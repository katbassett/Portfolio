import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

///Author: Kat Bassett

class PhotoPickerWidget extends StatefulWidget {
  const PhotoPickerWidget({Key? key}) : super(key: key);

  @override
  _PhotoPickerWidgetState createState() => _PhotoPickerWidgetState();
}

class _PhotoPickerWidgetState extends State<PhotoPickerWidget> {
  File? _selectedImage;
  final ImagePicker _picker = ImagePicker();

  Future<void> _pickImage() async {
    final XFile? pickedFile =
        await _picker.pickImage(source: ImageSource.gallery);

    if (pickedFile != null) {
      setState(() {
        _selectedImage = File(pickedFile.path);
      });
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('No image selected')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Photo Picker'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            _selectedImage == null
                ? const Text('No Image selected')
                : Image.file(
                    _selectedImage!,
                    width: 300,
                    height: 300,
                    fit: BoxFit.cover,
                  ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: _pickImage,
              child: const Text('Pick Image from Gallery'),
            ),
          ],
        ),
      ),
    );
  }
}
