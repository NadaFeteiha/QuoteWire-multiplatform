//
//  HomeScreen.swift
//  iosApp
//
//  Created by Nada Feteiha on 4/28/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI
import shared


struct HomeScreen: View {

    @StateObject var viewModel = HomeViewModel()

    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {

        NavigationView{
            
             ScrollView{
                 LazyVGrid(columns: gridColumns, spacing: 16){

                     ForEach(viewModel.images, id: \.id){quoteImage in
                         NavigationLink(destination: DetailScreen(quoteImage: quoteImage )) {
                             AsyncImage(url: URL(string: quoteImage.imageURL)){image in
                                 image.resizable()
                                     .aspectRatio(contentMode: .fill)
                                     .frame(height: 100)
                                     .cornerRadius(10)
                             }placeholder: {
                                 Color.gray
                             }
                         }

                     }

                     if viewModel.isLoading {
                         Section(footer: ProgressView()){}
                     }

                 }
                 .padding(.horizontal, 12)
             }
             .navigationTitle("Quotes ... ")
         }
         .task {
             await viewModel.loadImages()
         }
    }
}


