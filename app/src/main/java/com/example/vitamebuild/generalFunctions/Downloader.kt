package com.example.vitamebuild.generalFunctions

interface Downloader {
    fun downloadFile(url: String): Long
}