// photo_camera_widget.dart
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:image_gallery_saver/image_gallery_saver.dart';

///Author: Kat Bassett

class PhotoCameraWidget extends StatefulWidget {
  const PhotoCameraWidget({Key? key}) : super(key: key);

  @override
  _PhotoCameraWidgetState createState() => _PhotoCameraWidgetState();
}

class _PhotoCameraWidgetState extends State<PhotoCameraWidget> {
  File? _capturedImage;
  final ImagePicker _picker = ImagePicker();

  Future<void> _takePicture() async {
    final XFile? pickedFile =
        await _picker.pickImage(source: ImageSource.camera);

    if (pickedFile != null) {
      final File imageFile = File(pickedFile.path);
      final result = await ImageGallerySaver.saveFile(imageFile.path);
      print('Image saved to gallery: $result');

      setState(() {
        _capturedImage = imageFile;
      });
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('No image captured')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Camera'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            _capturedImage == null
                ? const Text('No image captured')
                : Image.file(
                    _capturedImage!,
                    width: 300,
                    height: 300,
                    fit: BoxFit.cover,
                  ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: _takePicture,
              child: const Text('Take Picture'),
            ),
          ],
        ),
      ),
    );
  }
}
